package com.luisu404.challengeliteratura.principal;

import com.luisu404.challengeliteratura.service.ConsumoApiService;

import java.util.Scanner;

public class Principal {

    Scanner lectura = new Scanner(System.in);

    private ConsumoApiService apiService;

    public Principal(ConsumoApiService apiService) {
        this.apiService = apiService;
    }


    public void mostrarMenu() {


        System.out.println(apiService.obtenerDatos("https://gutendex.com/books/?search=Declaration"));

        /*
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1. Buscar serie
                    2. Buscar episodio
                    3. Mostrar series buscadas
                    4. Buscar serie por nombre
                    5. Buscar Top 5 Serie
                    6. Buscar por categoria
                    7. Buscar series por Temporada y Evaluacion
                    8. Buscar episodio por titulo
                    9. Top 5 Episodios por Serie
                    0. Salir
                    """;
            System.out.println(menu);
            opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Opcion seleccionada 1: "+opcion);
                    break;
                case 2:
                    System.out.println("Opcion seleccionada 2: "+opcion);
                    break;
                case 3:
                    System.out.println("Opcion seleccionada 3: "+opcion);
                    break;
                case 4:
                    System.out.println("Opcion seleccionada 4: "+opcion);
                    break;
                case 5:
                    System.out.println("Opcion seleccionada 5: "+opcion);
                    break;

                case 6:
                    System.out.println("Opcion seleccionada 6: "+opcion);
                    break;
                case 7:
                    System.out.println("Opcion seleccionada 7: "+opcion);
                    break;
                case 8:
                    System.out.println("Opcion seleccionada 8: "+opcion);
                    break;
                case 9:
                    System.out.println("Opcion seleccionada: "+opcion);
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
*/
    }



}
