# Questions
Este es un plugin para juegos en el chat e interacción con los usuarios, en el que vas a poder modificar a tu gusto prácticamente cualquier función desde su configuración.

## ⚙️ Archivos de configuración
El plugin cuenta con 2 archivos de configuración, que determinarán como será la interactividad del plugin.

- 🔧 config: Cuenta con la configuración principal del plugin.
- 🤔 questions: Cuenta con la configuración de las preguntas que aparecerán.

## 🗂️ Config.yml
Este es el archivo principal de configuración, cuenta con los mensajes, recompensas, tiempos de espera, etc.

- `config:` Es el que tiene todos los datos, esta sería la clave que contiene toda la configuración.
  - `game:` Contiene la configuración de lo que aparecerá cuando se ejecute un juego en el chat.
    - `tittle:` Guarda el título que aparecerá cuando se ejecute un juego en el chat.
    - `sub_tittle:` Guarda el texto que aparecerá debajo del `tittle` cuando se ejecute un juego en el chat.
    - `motivational_phrase:` Guarda el texto que aparecerá debajo del `sub_tittle` cuando se ejecute un juego en el chat. **(Recibe nulo)**
    - `footer:` Guarda el texto que aparecerá de último cuando se ejecute un juego en el chat.
  - `finished_game:` Contiene la configuración de lo que aparecerá cuando termine o contesten un juego en el chat.
    - `when_the_users_do_not_answer:` Guarda el texto que aparecerá después de que acabe el tiempo de espera de una pregunta y ningún usuario la haya respondido.
    - `when_a_user_answers:` Guarda el texto que aparecerá después de que un usuario responda la pregunta.
  - `top:` Contiene la configuración del texto que aparecerá cuando se ejecute el comando `/qtop`
    - `tiitle:` Guarda el texto que aparecerá como título al ejecutar `/qtop`
    - `content:` Guarda el texto que aparecerá como contenido para cada jugador en el top al ejecutar `/qtop`
    - `footer:` Guarda el texto que aparecerá de último al ejecutar `/qtop`
    - `single_user:` Guarda el texto que aparecerá al ejecutar `/qtop <player>`
    - `in_case_the_player_is_not_found:` Guarda el texto que aparecerá cuando al ejecutar `/qtop <player>` no encuentra a un jugador.
  - `message_when_the_user_does_not_have_permissions:` Guarda el texto que aparecerá cuando un usuario intente ejecutar un comando y no tenga permisos.
  - `waiting_time:` Guarda el tiempo en segundos de cuanto va a esperar para que un usuario responda la pregunta. **(Recibe entero)**
  - `time_range_to_execute:` Guarda el tiempo en segundos cada cuanto se va a mostrar una pregunta en chat. **(Recibe entero)**
  - `number_of_users_to_run:` Guarda la cantidad de usuarios que debe haber en el servidor para que se comiencen a ejecutar las preguntas. **(Recibe entero)**
  - `time_to_save_data:` Guarda el tiempo en segundos de cada cuanto se va a hacer un guardado de los datos de los usuarios. **(Recibe entero)**
  - `rewards: ` Contiene las recompensas que tendrán las usuarios al contestar una pregunta. **(Contiene una lista de de recompensas)**
    - `- reward:` Contiene el comando que se ejecutará para entregar la recompensa al usuario.
    - `  probability: ` Contiene la probabilidad que tendrá de salir esta recompensa. **(Recibe entero y decimal)**

## 📝 Questions.yml
Este archivo contiene los juegos que aparecerán en el chat cuando inicie un juego. Contiene las preguntas, la respuesta y su tipo.

- Tipos de preguntas:
  - `Normal`: Los juegos con este tipo son preguntas para responder de manera normal.
  - `Sort`: Los juegos con este tipo son de ordenar la palabra.

- `questions:` Contiene todos los juegos o preguntas que aparecerán en chat. **(Contiene una lista de preguntas)**
  - `- question: ` Guarda lo que aparecerá como contenido del juego. **(Contiene una lista de texto)**
  - `  answer:` Guarda la respuesta de la pregunta.
  - `  type:` Guarda el tipo de pregunta o juego.

```
Ejemplo de questions.yml:
questions:
  - question:
    - "                 &f¡El primero en ordenar"
    - "              &e%question%. &fGanará el juego! &a✔"
    answer: "Cofre"
    type: "Sort"
```

## ✏️ Anotaciones
En los archivos de configuración hay diferentes anotaciones que se utilizan en los textos, sirven para que se tome texto importante del código que se quiere mostrar en las preguntas.

- `finished_game.when_the_users_do_not_answer:`
  - `%answer%` Toma la respuesta que apareció en el juego.
- `finished_game.when_a_user_answers:`
  - `%player%` Toma el usuario que respondió la pregunta.
  - `%seconds%` Toma los segundos en los que fue respondida la pregunta.
  - `%answer%` Toma la respuesta que apareció en el juego.
  - `%won%` Toma la cantidad de veces que ha ganado un juego el usuario que respondió.
- `top.content:`
  - `%place%` Toma el puesto en el que está el usuario.
  - `%player%` Toma al usuario en el top.
  - `%won%` Toma la cantidad de veces que ha ganado un juego el usuario del top.
- `top.single_user:`
  - `%prefix%` Toma el prefix que tiene el plugin.
  - `%place%` Toma el puesto en el que está el usuario.
  - `%player%` Toma al usuario en el top.
  - `%won%` Toma la cantidad de veces que ha ganado un juego el usuario del top.
- `top.in_case_the_player_is_not_found:`
  - `%prefix%` Toma el prefix que tiene el plugin.
- `rewards.reward:`
  - `%player%` Toma el jugador al que se entregará la recompensa mediante un comando.
- `questions.question:` **(Type: Sort)**
  - `%question%` Toma la respuesta y la desordena.

## 🧷 Comandos en Minecraft
- `/qhelp:` Muestra una guía de como usar los comandos.
- `/qtop:` Muestra el top con los primeros 10 usuarios.
- `/qtop <player>:` Muestra información del usuario colocado referente a este plugin.
- `/qenable:` En caso de que el plugin esté deshabilitado, lo pondrá en funcionamiento nuevamente.
- `/qdisable:` En caso de que el plugin esté habilitado, lo deshabilitará. **(Este comando también detiene el funcionamiento del guardado)**
- `/qreload <questions>:` Recarga la configuración del archivo questions.yml.
- `/qreload <config>:` Recarga la configuración del archivo config.yml.
- `/save:` Hace un guardado manual de los datos de los usuarios.
- `/save <enable>:` Habilita el guardado de datos de los usuarios, en caso de que esté deshabilitado.
- `/save <disable>:` Deshabilita el guardado de datos de los usuarios, en caso de que esté habilitado.

### 🫧 Permisos
-  `questions.admin` Tiene permisos para ejecutar todos los comandos.
-  `questions.user` Solo tiene permisos para ejecutar `/qtop`.

### 🎲 Probabilidades
Las probabilidades en las recompensas dependen de cuantas recompensas haya. Obviamente, si hay una sola recompensa, siempre será esa la que va a tocar. 

Recomendación
-  La sumatoria de las probabilidades debe dar 100.

### ❗ Puntos a tener en cuenta
-  Todo lo que contenga texto recibe acepta código de color normal y hexcolor.
-  Todo lo que contenga una lista debe recibir **si o si** una lista.
-  Todo lo que reciba entero solo recibirá entero.

## 💻 Desarrollador
El plugin fue desarrollado por `lsEmpty`, se aceptan críticas constructivas.
- [Youtube](https://www.youtube.com/@lsEmpty)
- [GitHub](https://github.com/lsEmpty)
- [Correo](mailto:danielxh794@gmail.com)
