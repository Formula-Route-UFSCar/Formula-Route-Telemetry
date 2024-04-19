/*
 * MIT License
 *
 * Copyright (c)2023 Matheus Markies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.formula;

import com.formula.manager.utilities.Save;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class FormulaRouteTelemetryMain extends Application {

    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        // Cria o diretório de aplicação, se necessário
        //FilesManager.applicationDirCreator();

        // Cria uma janela principal da aplicação
        var root = new ApplicationWindow();

        scene = new Scene(root, ApplicationWindow.MIN_WIDTH + 80, 768, false);
        scene.getStylesheets().add(getClass().getResource("/mspm/pages/DashboardCSS.css").toString());

        // Define a cena no palco
        stage.setScene(scene);

        // Define o título da janela
        stage.setTitle("Formula Route");

        // Adiciona um ícone à barra de título da janela
        Image appIcon = new Image(FormulaRouteTelemetryMain.class.getResource("/mspm/resources/Icone File.png").toString());
        stage.getIcons().add(appIcon);

        // Configura o comportamento de fechar da janela
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        // Maximize a janela
        stage.setMaximized(true);

        // Exibe a janela
        stage.show();
    }

    /**
     * Método principal da aplicação.
     * @param args os argumentos da linha de comando
     * @throws IOException se ocorrer um erro de E/S
     */
    public static void main(String[] args) throws IOException {
        // Configurações de sistema
        System.setProperty("http.keepAlive", "true");
        System.setProperty("prism.forceGPU", "true");
        System.setProperty("file.encoding", "UTF-8");

        // Inicia a aplicação JavaFX
        launch(args);
    }

    /**
     * Imprime uma representação mais amigável de uma pilha de exceção.
     * @param e a exceção a ser tratada
     */
    public static void printNicerStackTrace(Exception e) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();

        // Imprime o tipo e a mensagem da exceção
        System.err.println("Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        // Imprime a pilha de chamadas
        System.err.println("Stack trace:");

        for (StackTraceElement element : stackTraceElements) {
            // Imprime cada elemento da pilha de chamadas
            System.err.printf("  at %s.%s (%s:%d)%n",
                    element.getClassName(),
                    element.getMethodName(),
                    element.getFileName(),
                    element.getLineNumber());
        }
    }


}
