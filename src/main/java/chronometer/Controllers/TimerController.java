package chronometer.Controllers;

public class TimerController {
    Thread choronometerThread = new Thread(() -> {
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            seconds++;

            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }

            if (minutes == 60) {
                minutes = 0;
                hours++;
            }

            if (hours == 24) {
                hours = 0;
            }
        }
    });
}
