use esp_hal::delay::Delay;
use esp_hal::i2c::master::I2c;

use crate::register::i2c;

pub struct Mpu6050<'d, T>
where
    T: esp_hal::i2c::master::Instance + esp_hal::DriverMode,
{
    i2c: I2c<'d, T>,
    delay: Delay,
    address: u8,
}

#[derive(Debug)]
pub enum Mpu6050Error {
    I2cError,
    NotResponding,
    InvalidData,
}

#[derive(Debug, Clone, Copy)]
pub struct AccelData {
    pub x: f32,
    pub y: f32,
    pub z: f32,
}

#[derive(Debug, Clone, Copy)]
pub struct GyroData {
    pub x: f32,
    pub y: f32,
    pub z: f32,
}

#[derive(Debug, Clone, Copy)]
pub struct SensorData {
    pub accel: AccelData,
    pub gyro: GyroData,
    pub temperature: f32,
}

const MPU6050_ADDR: u8 = 0x68;
const PWR_MGMT_1: u8 = 0x6B;
const ACCEL_XOUT_H: u8 = 0x3B;
const GYRO_XOUT_H: u8 = 0x43;
const TEMP_OUT_H: u8 = 0x41;
const WHO_AM_I: u8 = 0x75;

impl<'d, T> Mpu6050<'d, T>
where
    T: esp_hal::i2c::master::Instance + esp_hal::DriverMode,
{
    pub fn new(i2c: I2c<'d, T>, delay: Delay) -> Self {
        Self {
            i2c,
            delay,
            address: MPU6050_ADDR,
        }
    }

    pub fn init(&mut self) -> Result<(), Mpu6050Error> {
        let who_am_i = self.read_register(WHO_AM_I)?;
        if who_am_i != 0x68 {
            return Err(Mpu6050Error::NotResponding);
        }

        self.write_register(PWR_MGMT_1, 0x00)?;
        self.delay.delay_millis(100);

        Ok(())
    }

    pub fn read_accel(&mut self) -> Result<AccelData, Mpu6050Error> {
        let mut buffer = [0u8; 6];
        self.read_registers(ACCEL_XOUT_H, &mut buffer)?;

        let x_raw = i16::from_be_bytes([buffer[0], buffer[1]]);
        let y_raw = i16::from_be_bytes([buffer[2], buffer[3]]);
        let z_raw = i16::from_be_bytes([buffer[4], buffer[5]]);

        const ACCEL_SCALE: f32 = 16384.0;

        Ok(AccelData {
            x: x_raw as f32 / ACCEL_SCALE,
            y: y_raw as f32 / ACCEL_SCALE,
            z: z_raw as f32 / ACCEL_SCALE,
        })
    }

    pub fn read_gyro(&mut self) -> Result<GyroData, Mpu6050Error> {
        let mut buffer = [0u8; 6];
        self.read_registers(GYRO_XOUT_H, &mut buffer)?;

        let x_raw = i16::from_be_bytes([buffer[0], buffer[1]]);
        let y_raw = i16::from_be_bytes([buffer[2], buffer[3]]);
        let z_raw = i16::from_be_bytes([buffer[4], buffer[5]]);

        const GYRO_SCALE: f32 = 131.0;

        Ok(GyroData {
            x: x_raw as f32 / GYRO_SCALE,
            y: y_raw as f32 / GYRO_SCALE,
            z: z_raw as f32 / GYRO_SCALE,
        })
    }

    pub fn read_temperature(&mut self) -> Result<f32, Mpu6050Error> {
        let mut buffer = [0u8; 2];
        self.read_registers(TEMP_OUT_H, &mut buffer)?;

        let temp_raw = i16::from_be_bytes([buffer[0], buffer[1]]);
        let temperature = (temp_raw as f32 / 340.0) + 36.53;

        Ok(temperature)
    }

    pub fn read_all(&mut self) -> Result<SensorData, Mpu6050Error> {
        let accel = self.read_accel()?;
        let gyro = self.read_gyro()?;
        let temperature = self.read_temperature()?;

        Ok(SensorData {
            accel,
            gyro,
            temperature,
        })
    }

    fn write_register(&mut self, register: u8, value: u8) -> Result<(), Mpu6050Error> {
        i2c::write(&mut self.i2c, self.address, register, value).map_err(|_| Mpu6050Error::I2cError)
    }

    fn read_register(&mut self, register: u8) -> Result<u8, Mpu6050Error> {
        i2c::read(&mut self.i2c, self.address, register).map_err(|_| Mpu6050Error::I2cError)
    }

    fn read_registers(&mut self, register: u8, buffer: &mut [u8]) -> Result<(), Mpu6050Error> {
        i2c::read_mul(&mut self.i2c, self.address, register, buffer).map_err(|_| Mpu6050Error::I2cError)
    }
}
