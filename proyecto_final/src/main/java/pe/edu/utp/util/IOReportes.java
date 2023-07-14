package pe.edu.utp.util;

import pe.edu.utp.DataSismos;

public class IOReportes {
    public enum TIPO {ASCII, HTML5}
    public enum NOMBREJS {GRAFICOS, PORCENTAJES}
    static int contCoincidencias = 0;
    public static StringBuilder makeReport(TIPO tipo, DataSismos[] lista) {
        if (tipo == TIPO.HTML5) {
            return makeHTML5Report(lista);
        } else {
            return makeASCIIReport(lista);
        }
    }

    public static StringBuilder makeJs(NOMBREJS nombreArchivoJs) {
        if (nombreArchivoJs == NOMBREJS.PORCENTAJES) {
            return makeJavaScript();
        }
        return null;
    }

    private static StringBuilder makeASCIIReport(DataSismos[] lista) {
        return new StringBuilder("d");
    }

    private static StringBuilder makeHTML5Report(DataSismos[] lista) {
        StringBuilder sb = new StringBuilder();
        String header = """
            <!doctype html>
            <html lang="es">
            <head>
              <meta charset="UTF-8">
              <meta name="viewport"
                    content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
              <meta http-equiv="X-UA-Compatible" content="ie=edge">
              <!-- CDN CSS Bootstrap -->
              <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
              <!-- CSS propios -->
              <link rel="stylesheet" href="css/style.css">
              <!-- Favicon -->
              <link rel="icon" href="img/favicon.png" type="image/png">
              <title>Plantilla de reportes</title>
            </head>
            <body>
              <!-- Estructura (Creador: Juan Bladimir Romero Collazos) -->
              <!-- Tabla (Creador: Juan Bladimir Romero Collazos) -->
              <label>REPORTE DE CATALOGO SISMICO 1960-2021 (IGP)</label>
              <table>
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Fecha UTC</th>
                    <th>Hora UTC</th>
                    <th>Latitud</th>
                    <th>Longitud</th>
                    <th>Profundidad</th>
                    <th>Magnitud</th>
                    <th>Fecha de corte</th>
                  </tr>
                </thead>
            """;
        sb.append(header);

        String data = """
            <tbody>
              <tr>
                <td>{{id}}</td>
                <td>{{fecha_utc}}</td>
                <td>{{hora_utc}}</td>
                <td>{{latitud}}</td>
                <td>{{longitud}}</td>
                <td>{{profundidad}}</td>
                <td>{{magnitud}}</td>
                <td>{{fecha_corte}}</td>
              </tr>
            </tbody>
            """;

        for (DataSismos dataSismos : lista) {
            String strSismos = data.replace("{{id}}", String.valueOf(dataSismos.getId()))
                    .replace("{{fecha_utc}}", dataSismos.getFechaUTC())
                    .replace("{{hora_utc}}", dataSismos.getHoraUTC())
                    .replace("{{latitud}}", String.valueOf(dataSismos.getLatitud()))
                    .replace("{{longitud}}", String.valueOf(dataSismos.getLongitud()))
                    .replace("{{profundidad}}", String.valueOf(dataSismos.getProfundidad()))
                    .replace("{{magnitud}}", String.valueOf(dataSismos.getMagnitud()))
                    .replace("{{fecha_corte}}", dataSismos.getFechaCorte());
            sb.append(strSismos);
            contCoincidencias++;
        }

        String footer = """
            <tfoot>
              <tr>
                <td class="label-footer">Número de coincidencias</td>
                <td>{{numero_coincidencias}}</td>
                <td class="label-footer">Porcentaje de coincidencias</td>
                <td id="porcentaje"></td>
              </tr>
            </tfoot>
          </table>

          <!-- Scritps (Creador: Juan Bladimir Romero Collazos) -->
          <!-- JS propios -->
          <script src="js/graficos.js"></script>
          <script src="js/porcentaje.js"></script>
          <!-- CDN JS Bootstrap -->
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
          <!-- Apache ECharts -->
          <script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/5.4.2/echarts.min.js" integrity="sha512-VdqgeoWrVJcsDXFlQEKqE5MyhaIgB9yXUVaiUa8DR2J4Lr1uWcFm+ZH/YnzV5WqgKf4GPyHQ64vVLgzqGIchyw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        </body>
        </html>
        """;
        sb.append(footer.replace("{{numero_coincidencias}}", String.format("%s", contCoincidencias)));
        return sb;
    }
    private static StringBuilder makeJavaScript() {
        StringBuilder sb = new StringBuilder();
        String js = """
                // procentajes (Creador: Juan Bladimir Romero Collazos)
                                
                const totalDatos = 22712;
                const numeroCoincidencias = {{numero_coincidencias}};
                const porcentaje = (100 * numeroCoincidencias) / totalDatos;
                const porcentajeCelda = document.getElementById("porcentaje");
                                
                porcentajeCelda.textContent = `${porcentaje.toFixed(2)}%`
                """;
        sb.append(js.replace("{{numero_coincidencias}}", String.format("%s", contCoincidencias)));
        return sb;
    }
}
