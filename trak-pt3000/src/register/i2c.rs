use esp_hal::i2c::master::I2c;

pub enum I2cError {
    Read,
    Write,
}

pub fn read<T: esp_hal::i2c::master::Instance + esp_hal::DriverMode>(
    i2c: &mut I2c<T>,
    address: u8,
    register: u8,
) -> Result<u8, I2cError> {
    let mut buffer = [0u8; 1];
    i2c.write_read(address, &[register], &mut buffer)
        .map_err(|_| I2cError::Read)?;

    Ok(buffer[0])
}

pub fn read_mul<T: esp_hal::i2c::master::Instance + esp_hal::DriverMode>(
    i2c: &mut I2c<T>,
    address: u8,
    register: u8,
    buffer: &mut [u8],
) -> Result<(), I2cError> {
    i2c.write_read(address, &[register], buffer)
        .map_err(|_| I2cError::Read)
}

pub fn write<T: esp_hal::i2c::master::Instance + esp_hal::DriverMode>(
    i2c: &mut I2c<T>,
    address: u8,
    register: u8,
    value: u8,
) -> Result<(), I2cError> {
    i2c.write(address, &[register, value])
        .map_err(|_| I2cError::Write)
}