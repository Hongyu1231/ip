package batman;

import commands.*;
import exception.BatmanException;
import task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class make sense of user's input
 * It will slice the user input and feed the output to Command object
 */
public class Parser {

    /**
     * Parses the user's input string and translates it into the corresponding Command object.
     *
     * @param input The full string input typed by the user.
     * @param ui    The user interface.
     * @return A Command object that represents the user's intention (e.g., AddCommand, DeleteCommand).
     * @throws BatmanException If the user input format is invalid, missing arguments, or contains unknown commands.
     */
    public static Command parse(String input, Ui ui) {
        // The array of input by user
        String[] words = input.split(" ");

        // Handle empty input (e.g., user just pressed Enter)
        if (input.trim().isEmpty()) {
            throw new BatmanException("     Please add a command.");
        }

        String command = words[0];

        if (command.equals("bye")) {
            if (words.length != 1) {
                throw new BatmanException("     There is no need add any argument after bye.");
            }
            return new ByeCommand();
        } else if (command.equals("list")) {
            if (words.length != 1) {
                throw new BatmanException("     There is no need add any argument after list.");
            }
            return new ListCommand();
        } else if (command.equals("mark")) {
            if (words.length != 2) {
                throw new BatmanException("     Please provide 1 number for task index.");
            }

            try {
                int index = Integer.parseInt(words[1]) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new BatmanException("     Please provide a valid NUMBER for task index.");
            }
        } else if (command.equals("unmark")) {
            if (words.length != 2) {
                throw new BatmanException("     Please provide 1 number for task index.");
            }

            try {
                int index = Integer.parseInt(words[1]) - 1;
                return new UnMarkCommand(index);
            } catch (NumberFormatException e) {
                throw new BatmanException("     Please provide a valid NUMBER for task index.");
            }

        } else if (command.equals("delete")) {
            if (words.length != 2) {
                throw new BatmanException("     Please provide 1 number for task index.");
            }

            try {
                int index = Integer.parseInt(words[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new BatmanException("     Please provide a valid NUMBER for task index.");
            }

        } else if (command.equals("todo")) {
            // substring(5) skips "todo" (length 5)
            if (input.length() <= 5) {
                throw new BatmanException("     Error: The description of todo cannot be empty. Please add a description after todo.");
            }

            String todoDescription = input.substring(5).trim();

            return new AddCommand(new Todo(todoDescription));
        } else if (command.equals("deadline")) {
            int byIndex = input.indexOf(" /by ");
            if (byIndex == -1) {
                throw new BatmanException("     Error: Invalid format. Please use 'deadline <description> /by <time>'.");
            }

            // substring(9) skips "deadline " (length 8)
            String deadlineDescription = input.substring(8, byIndex).trim();
            String by = input.substring(byIndex + 5).trim(); // +5 skips " /by "

            if (deadlineDescription.isEmpty()) {
                throw new BatmanException("     Error: The description of deadline cannot be empty. Please add a description after deadline.");
            }

            if (by.isEmpty()) {
                throw new BatmanException("     Error: The time of \"by\" cannot be empty. Please add a time after \"by\".");
            }

            return new AddCommand(new Deadline(deadlineDescription, by));
        } else if (command.equals("event")) {
                int fromIndex = input.indexOf(" /from ");
                int toIndex = input.indexOf(" /to ");
                if (fromIndex == -1 || toIndex == -1) {
                    throw new BatmanException("     Error: Invalid format. Please use 'event <description> /from <time> /to <time>'.");
                }

                if (fromIndex > toIndex) {
                    throw new BatmanException("     Error: Invalid format. '/from' must be put before '/to'.");
                }

                if (fromIndex + 6 == toIndex) {
                    throw new BatmanException("     Error: The time of \"from\" cannot be empty. Please add a time after \"from\".");
                }

                // substring(6) skips "event " (length 5)
                String eventDescription = input.substring(5, fromIndex).trim();
                String from = input.substring(fromIndex + 7, toIndex).trim(); // +7 skips " /from "

                String to = input.substring(toIndex + 5).trim(); // +5 skips " /to "

                if (eventDescription.isEmpty()) {
                    throw new BatmanException("     Error: The description of event cannot be empty. Please add a description after event.");
                }

                if (from.isEmpty()) {
                    throw new BatmanException("     Error: The time of \"from\" cannot be empty. Please add a time after \"from\".");
                }

                if (to.isEmpty()) {
                    throw new BatmanException("     Error: The time of \"to\" cannot be empty. Please add a time after \"to\".");
                }

                return new AddCommand(new Event(eventDescription, from, to));
            }

        else if (command.equals("view")) {
            if (words.length != 2) {
                throw new BatmanException("     Error: Invalid format. Please use 'view yyyy-MM-dd' (e.g., view 2019-10-15).");
            }
            try {
                LocalDate targetDate = LocalDate.parse(words[1]);
                return new ViewCommand(targetDate);
            } catch (DateTimeParseException e) {
                throw new BatmanException("     Error: Invalid date format. Please use yyyy-MM-dd (e.g., 2019-10-15).");
            }
        }
        else if (command.equals("find")) {
            if (words.length != 2) {
                throw new BatmanException("     Error: Invalid format. Please use 'find <keyWord>'.");
            }
            return new FindCommand(words[1]);
        }

            else {
                throw new BatmanException("     I'm sorry, but I don't know what that means :-(");
            }
    }

    /**
     * Try to parse the time of event and deadline objects
     * If the time can't be parsed (e.g. User keyed in "Sunday"), then return null
     *
     * @param dateTimeStr The description string
     * @return A DateTime object represent the time
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter[] dateTimeFormatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        };

        DateTimeFormatter[] dateFormatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd")
        };

        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException e) {}
        }

        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                LocalDate justDate = LocalDate.parse(dateTimeStr, formatter);
                return justDate.atStartOfDay();
            } catch (DateTimeParseException e) {}
        }
        return null;
    }
}
