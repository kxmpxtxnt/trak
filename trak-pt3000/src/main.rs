#![no_std]
#![no_main]
#![deny(
    clippy::mem_forget,
    reason = "mem::forget is generally not safe to do with esp_hal types, especially those \
    holding buffers for the duration of a data transfer."
)]
mod modules;

use crate::modules::hc_sr04::HcSr04;
use core::panic::PanicInfo;
use embedded_dht_rs::dht11::Dht11;
use esp_hal::delay::Delay;
use esp_hal::gpio::{DriveMode, Level, Output, OutputConfig};
use esp_hal::rng::Rng;
use esp_hal::{clock::CpuClock, main, rmt::Rmt, time::Rate};
use esp_hal_smartled::{smart_led_buffer, SmartLedsAdapter};
use esp_println::println;
use smart_leds::{SmartLedsWrite, RGB8};

esp_bootloader_esp_idf::esp_app_desc!();

#[main]
fn main() -> ! {
    let config = esp_hal::Config::default().with_cpu_clock(CpuClock::max());
    let peripherals = esp_hal::init(config);
    let rmt = Rmt::new(peripherals.RMT, Rate::from_mhz(80)).expect("Failed to initialize RMT.");

    let mut buffer = smart_led_buffer!(1);
    let mut led = SmartLedsAdapter::new(rmt.channel0, peripherals.GPIO8, &mut buffer);

    let mut color = RGB8::new(255, 255, 255);

    let mut output = Output::new(
        peripherals.GPIO2,
        Level::Low,
        OutputConfig::default().with_drive_mode(DriveMode::OpenDrain),
    )
        .into_flex();

    output.set_input_enable(true);
    output.set_output_enable(true);

    let delay = Delay::new();

    let mut dht11 = Dht11::new(output, delay);
    let mut hcsr04 = HcSr04::new(peripherals.GPIO5, peripherals.GPIO4, delay);

    let rng = Rng::new();

    loop {
        delay.delay_millis(500);

        let temp: Option<u8> = match dht11.read() {
            Ok(reading) => Some(reading.temperature),
            Err(err) => {
                println!("Error reading from dht11: {:?}", err);
                None
            }
        };

        let measurement = hcsr04.measure(temp);

        match hcsr04.measure(temp) {
            Ok((distance, temperature)) => {
                println!("Measuring {:.2}cm at {}°C", distance, temperature);
            }
            Err(err) => println!("Error while measuring distance: {:?}.", err),
        }

        color = RGB8::new(rng.random() as u8, rng.random() as u8, rng.random() as u8);
        led.write([color].into_iter()).unwrap();
    }
}

#[panic_handler]
fn panic(info: &PanicInfo) -> ! {
    loop {
        println!("Panic {}", info.message())
    }
}
