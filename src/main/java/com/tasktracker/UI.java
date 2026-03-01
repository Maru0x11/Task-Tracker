package com.tasktracker;

// A basic Ui class using the ansi escape codes to better the UI/UX

public class UI {
    public static final String RESET   = "\033[0m";
    public static final String BOLD    = "\033[1m";
    public static final String GREEN   = "\033[32m";
    public static final String YELLOW  = "\033[33m";
    public static final String CYAN    = "\033[36m";
    public static final String RED     = "\033[31m";
    public static final String WHITE   = "\033[37m";
    public static final String DIM     = "\033[2m";

    public static void header(String text) {
        System.out.println("\n" + BOLD + CYAN + "╔══════════════════════════════╗" + RESET);
        System.out.println(BOLD + CYAN + "  " + text + RESET);
        System.out.println(BOLD + CYAN + "╚══════════════════════════════╝" + RESET);
    }

    public static void subHeader(String text) {
        System.out.println("\n" + BOLD + YELLOW + "── " + text + " ──" + RESET);
    }

    public static void option(String text) {
        System.out.println(WHITE + "  " + text + RESET);
    }

    public static void ok(String text) {
        System.out.println(GREEN + "\n  ✓ " + text + RESET);
    }

    public static void error(String text) {
        System.out.println(RED + "\n  ✗ " + text + RESET);
    }

    public static void what(String text) {
        System.out.println(CYAN + "  → " + text + RESET);
    }

    public static void prompt(String text) {
        System.out.print(YELLOW + "  » " + text + ": " + RESET);
    }

    public static void divider() {
        System.out.println(DIM + "  ──────────────────────────────" + RESET);
    }

    public static void warning(String text) {
        System.out.println(YELLOW + "  ⚠ " + text + RESET);
    }
}