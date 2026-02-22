package batman;

import commands.*;
import exception.BatmanException;
import task.*;

public class Parser {
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
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new BatmanException("     Error: Can't find \"/by\". Please add \"by\" after description.");
            }

            // substring(9) skips "deadline " (length 9)
            String deadlineDescription = input.substring(9, byIndex).trim();

            if (input.length() < byIndex + 4) {
                throw new BatmanException("     Error: The time of \"by\" cannot be empty. Please add a time after \"by\".");
            }

            String by = input.substring(byIndex + 4).trim(); // +4 skips "/by "

            if (deadlineDescription.isEmpty()) {
                throw new BatmanException("     Error: The description of deadline cannot be empty. Please add a description after deadline.");
            }

            if (by.isEmpty()) {
                throw new BatmanException("     Error: The time of \"by\" cannot be empty. Please add a time after \"by\".");
            }

            if (input.charAt(byIndex + 3) != ' ') {
                throw new BatmanException("     Error: Must add a \" \" after \"/by\".");
            }

            return new AddCommand(new Deadline(deadlineDescription, by));
        } else if (command.equals("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                if (fromIndex == -1) {
                    throw new BatmanException("     Error: Can't find \"/from\". Please add \"/from\" after description.");
                }
                else if (toIndex == -1) {
                    throw new BatmanException("     Error: Can't find \"/to\". Please add \"/to\" after description.");
                }

                // substring(6) skips "event " (length 6)
                String eventDescription = input.substring(6, fromIndex).trim();
                String from = input.substring(fromIndex + 6, toIndex).trim(); // +6 skips "/from "

            if (input.length() < toIndex + 4) {
                throw new BatmanException("     Error: The time of \"to\" cannot be empty. Please add a time after \"to\".");
            }

                String to = input.substring(toIndex + 4).trim(); // +4 skips "/to "

                if (eventDescription.isEmpty()) {
                    throw new BatmanException("     Error: The description of event cannot be empty. Please add a description after event.");
                }

                if (from.isEmpty()) {
                    throw new BatmanException("     Error: The time of \"from\" cannot be empty. Please add a time after \"from\".");
                }

                if (to.isEmpty()) {
                    throw new BatmanException("     Error: The time of \"to\" cannot be empty. Please add a time after \"to\".");
                }

                if (input.charAt(fromIndex + 5) != ' ') {
                    throw new BatmanException("     Error: Must add a \" \" after \"/from\".");
                }

                if (input.charAt(toIndex + 3) != ' ') {
                    throw new BatmanException("     Error: Must add a \" \" after \"/to\".");
                }

                return new AddCommand(new Event(eventDescription, from, to));
            }

            else {
                throw new BatmanException("     I'm sorry, but I don't know what that means :-(");
            }
    }
}
