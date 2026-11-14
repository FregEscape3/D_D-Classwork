import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactive navigation of the file system
 * This class reads commands from standard input, interprets them,
 * and invokes operations on the current directory node.
 */
public class Navigator {

    private final FileSystemTree fileSystem;
    private FolderNode currentDirectory;
    private boolean shouldExit;

    /**
     * Constructs a navigator for a given file system tree.
     * The starting location is the root directory.
     */
    public Navigator(FileSystemTree fst) {
        this.fileSystem = fst;
        this.currentDirectory = fst.getRoot();
    }

    /**
     * Starts a command loop that repeatedly reads a line of input,
     * interprets it as a command with arguments, and executes it until
     * a request to terminate is received.
     */
    public void run() {
        shouldExit = false;
        Scanner kb = new Scanner(System.in);

        while (!shouldExit) {
            // Prompt can be customized to show current path if desired.
            String line = kb.nextLine();
            processUserInputString(line);
        }

        kb.close();
    }

    /**
     * Executes the commands in a txt file, repeatedly reading a single line,
     * interpreting it as a command with arguments, and executing it until the end
     * of the file
     */
    public void run(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));
        while (reader.hasNext()) {
            String line = reader.nextLine();
            processUserInputString(line);
        }
        reader.close();
    }

    /**
     * Changes the current directory based on a single path argument.
     * Behavior should mirror typical Unix shells:
     * - "." refers to the current directory (no change).
     * - ".." moves to the parent directory (if one exists).
     * - Paths starting with "/" are interpreted from the root directory.
     * - Other paths are interpreted relative to the current directory.
     */
    private void cd(String[] args) {
        if (args.length <= 0) {
            System.out.println("No argument inputted. Please try again.");
            return;
        }
        String path = args[0];
        FolderNode destination = currentDirectory;
        if (path.charAt(0) == '/') {
            destination = fileSystem.getRoot();
        }
        int start = 0;
        while (start < path.length()) {
            int end = path.indexOf("/", start);
            if (end == -1) {
                end = path.length();
            }
            String part = path.substring(start, end);
            start = end + 1;
            if (!part.isEmpty() && !part.equals(".")) {
                if (part.equals("..")) {
                    if (destination.getParent() != null) {
                        destination = destination.getParent();
                    }
                } else {
                    FileSystemNode child = destination.getChildByName(part);
                    if (child == null) {
                        System.out.println("No such directory: " + path);
                        return;
                    }
                    if (!child.isFolder()) {
                        System.out.println("Not a directory: " + part);
                        return;
                    }
                    destination = (FolderNode) child;
                }
            }
        }
        currentDirectory = destination;
    }

    /**
     * Lists all items contained directly in the current directory.
     * Output formatting can mirror typical file system listings.
     */
    private void ls(String[] args) {
        // TODO: print names of all child nodes of currentDirectory
        for (FileSystemNode child : currentDirectory.getChildren()) {
            System.out.println(child);
        }
    }

    /**
     * Creates a new directory inside the current directory using the provided name.
     */
    private void mkdir(String[] args) {
        // TODO: read folder name from args and delegate to
        // currentDirectory.addFolder(...)
        currentDirectory.addFolder(args[0]);
    }

    /**
     * Creates a new file inside the current directory with a given name and size.
     */
    private void touch(String[] args) {
        // TODO: read file name and size from args and delegate to
        // currentDirectory.addFile(...)
        currentDirectory.addFile(args[0], Integer.parseInt(args[1]));
    }

    /**
     * Searches the current directory and its descendants for nodes with a given
     * name
     * and prints their paths.
     */
    private void find(String[] args) {
        // TODO: use recursive search starting at currentDirectory
        FolderNode head = currentDirectory;
        if (head.getName().equals(args[0])) {
            System.out.println(head.toString());
            return;
        }
        if (head.containsNameRecursive(args[0])) {
            return;
        }
        System.out.println("Directory not found.");
    }

    /**
     * Prints the absolute path of the current directory, from the root to this
     * node.
     */
    private void pwd(String[] args) {
        // TODO: use currentDirectory.toString() or similar path builder
        System.out.println(currentDirectory.toString());
    }

    /**
     * Displays the contents of the current directory as a tree, optionally
     * respecting flags or depth limits if provided by the arguments.
     */
    // in real life, -l number = limit depth, -d = limit to only directories
    private void tree(String[] args) {
        int maxDepth = Integer.MAX_VALUE;
        boolean directoriesOnly = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directoriesOnly = true;
            } else if (args[i].equals("-l") && i + 1 < args.length) {
                String level = args[i + 1];
                if (level.charAt(0) == '-') {
                    System.out.println("Level cannot be negative.");
                    return;
                }
                int start = 0;
                if (level.charAt(0) == '+') {
                    start = 1;
                }
                if (start == level.length()) {
                    System.out.println("Invalid level: " + level);
                    return;
                }
                maxDepth = 0;
                for (int j = start; j < level.length(); j++) {
                    int digit = level.charAt(j) - '0';
                    if (digit < 0 || digit > 9 || maxDepth > (Integer.MAX_VALUE - digit) / 10) {
                        System.out.println("Invalid level: " + level);
                        return;
                    }
                    maxDepth = maxDepth * 10 + digit;
                }
            } else {
                System.out.println("Usage: tree [-l <level>] [-d]");
                return;
            }
        }
        printTree(currentDirectory, "", maxDepth, directoriesOnly);
    }

    private void printTree(FolderNode folder, String prefix, int levels, boolean directoriesOnly) {
        if (levels == 0) {
            return;
        }
        ArrayList<FileSystemNode> children = new ArrayList<>();
        for (FileSystemNode child : folder.getChildren()) {
            if (!directoriesOnly || child.isFolder()) {
                children.add(child);
            }
        }
        for (int i = 0; i < children.size(); i++) {
            FileSystemNode child = children.get(i);
            String branch = "├── ";
            String childPrefix = prefix + "│   ";
            if (i == children.size() - 1) {
                branch = "└── ";
                childPrefix = prefix + "    ";
            }
            System.out.println(prefix + branch + child.getName());
            if (child.isFolder()) {
                printTree((FolderNode) child, childPrefix, levels - 1, directoriesOnly);
            }
        }
    }

    /**
     * Prints how many nodes (files and folders) exist in the current directory
     * and all of its subdirectories.
     */
    private void count(String[] args) {
        // TODO: call a counting method on currentDirectory
        System.out.println(currentDirectory.getTotalNodeCount());
    }

    /**
     * Prints the total size of all files reachable from the current directory.
     */
    private void size(String[] args) {
        // TODO: call a size-calculation method on currentDirectory
        System.out.println(currentDirectory.getSize());
    }

    /**
     * Prints the depth of the current directory, defined as the number of edges
     * from the root directory down to this directory.
     */
    private void depth(String[] args) {
        // TODO: use a depth method on currentDirectory
        System.out.println(currentDirectory.getDepth());
    }

    /**
     * Prints the height of the current directory, defined as the longest downward
     * distance from this directory to any file or subdirectory beneath it.
     * An empty directory has value 0.
     */
    private void height(String[] args) {
        // TODO: use a height method on currentDirectory
        System.out.println(currentDirectory.getHeight());
    }

    /**
     * Signals that the interactive loop should terminate after the current command.
     */
    private void quit(String[] args) {
        shouldExit = true;
    }

    /**
     * Interprets a line of user input by splitting it into a command and arguments,
     * then forwarding control to the appropriate helper method.
     *
     * Example inputs and how they are interpreted:
     * "ls"
     * -> command: "ls"
     * args: []
     *
     * "mkdir docs"
     * -> command: "mkdir"
     * args: ["docs"]
     *
     * "touch notes.txt 100"
     * -> command: "touch"
     * args: ["notes.txt", "100"]
     *
     * "cd .."
     * -> command: "cd"
     * args: [".."]
     */
    public void processUserInputString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.trim().split("\\s+");
        String command = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        switch (command) {
            // cd <folder> : changes directory to the given folder
            // If folder is ".", goes into parent folder
            // If folder is "..", goes into root folder
            case "cd":
                cd(args);
                break;
            case "ls":
                ls(args);
                break;
            // mkdir <name> : makes a directory (folder) in the current directory with the
            // given name
            case "mkdir":
                mkdir(args);
                break;
            // touch <name> <size> : makes a file in the current directory with the given
            // name and given size
            case "touch":
                touch(args);
                break;
            // find <name> : prints the path of every file or folder in this one and its
            // subfolders with the given name
            case "find":
                find(args);
                break;
            // pwd : prints the total path of the current directory
            case "pwd":
                pwd(args);
                break;
            // tree -l [level] -d : Prints the contents of the folder
            // and the contents of the folders within etc. as a tree, e.g.:
            /*
             * ├── file0
             * ├── folder0
             * │ ├── folderInsideThat
             * │ │ └── fileInAboveFolder
             * │ └── fileInFolder0
             * └── emptyFolder
             */
            // Note the spacing and funky characters; VSCode's autoformatting messes
            // with the above spacing, so please see the text document on the Hub for proper
            // formatting
            case "tree":
                tree(args);
                break;
            /* Note that all of the below commands are non-standard */
            // count : prints the total count of all files and folders included in the
            // current directory and its subfolders
            case "count":
                count(args);
                break;
            // size : prints the total size of all files included in the current directory
            // and its subfolders
            case "size":
                size(args);
                break;
            // depth : prints the depth of the current directory, i.e. 0 if it's the root
            case "depth":
                depth(args);
                break;
            // height : prints the height of the current directory, i.e. 0 if it has no
            // files or folders
            case "height":
                height(args);
                break;
            // quit : sets shouldExit to false
            case "quit":
                quit(args);
                break;
            default:
                // Unknown commands can be reported back to the user.
                System.out.println("Unrecognized command: " + command);
        }
    }
}
