#![no_std]
#![no_main]
#![deny(
    clippy::mem_forget,
    reason = "mem::forget is generally not safe to do with esp_hal types, especially those \
    holding buffers for the duration of a data transfer."
)]
extern crate alloc;

use core::panic::PanicInfo;
use esp_hal::main;
use esp_println::println;

mod modules;
mod test_board;
mod register;

#[main]
fn main() -> ! {
    loop {}
}

#[panic_handler]
fn panic(info: &PanicInfo) -> ! {
    loop {
        println!("Panic {}", info.message())
    }
}
