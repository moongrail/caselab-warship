package com.caselab.warship;

import com.caselab.warship.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.util.Scanner;

import static com.caselab.warship.util.MenuPrintUtil.getPlayerInput;
import static com.caselab.warship.util.MenuPrintUtil.getValidCoordinates;

@SpringBootApplication
@RequiredArgsConstructor
public class WarshipApplication implements CommandLineRunner {
    private final GameService gameService;

    public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WarshipApplication.class);
		app.setBanner(new CustomBanner());
		app.run(args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean isGameOver = false;

        System.out.println("Hello в Морской Cringe!");
        gameService.startGame();

        while (!isGameOver) {
            int player = getPlayerInput(scanner);

            gameService.printPlayerBoard(player);

            int[] coordinates = getValidCoordinates(scanner);
            String result = gameService.playTurn(player, coordinates[0], coordinates[1]);

            System.out.println(result);
            isGameOver = gameService.checkGameOver();

            if (isGameOver) {
                System.out.println("Игра окончена! ");
				System.out.println(SpringApplication.BANNER_LOCATION_PROPERTY_VALUE);
            }
        }
    }

	static class CustomBanner implements Banner {
		@Override
		public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
			out.println(" =0 ");
		}
	}
}
