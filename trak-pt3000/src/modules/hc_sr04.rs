use esp_hal::delay::Delay;
use esp_hal::gpio::{Input, InputConfig, InputPin, Level, Output, OutputConfig, OutputPin, Pull};

pub struct HcSr04<'io> {
    trigger: Output<'io>,
    echo: Input<'io>,
    delay: Delay,
}

impl<'io> HcSr04<'io> {
    pub fn new(trigger_pin: impl OutputPin + 'io, echo_pin: impl InputPin + 'io, delay: Delay) -> Self {
        Self {
            trigger: Output::new(trigger_pin, Level::Low, OutputConfig::default()),
            echo: Input::new(echo_pin, InputConfig::default().with_pull(Pull::Down)),
            delay,
        }
    }

    pub fn measure(&mut self, temperature: Option<u8>) -> Option<f32> {
        self.trigger.set_high();
        self.delay.delay_micros(10);
        self.trigger.set_low();

        let mut timeout = 0;
        while self.echo.is_low() {
            self.delay.delay_micros(1);
            timeout += 1;
            if timeout >= 10_000 {
                return None;
            }
        }

        let mut duration: u32 = 0;
        while self.echo.is_high() {
            self.delay.delay_micros(1);
            duration += 1;
            if duration >= 30_000 {
                return None;
            }
        }

        let temp = temperature.unwrap_or(20) as f32;

        let speed_factor = (331.3 + 0.606 * temp) / 10_000.0;
        let distance_cm = duration as f32 * speed_factor;

        Some(distance_cm)
    }
}
