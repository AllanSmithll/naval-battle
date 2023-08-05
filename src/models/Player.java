/*
 * Player class
 * @version 1.1.1
 * powered by @author Allan Amancio
 */
package models;

public class Player {
    private String name;

    public Player(String p_name) {
        this.name = p_name;
    }

    public String getName() { return this.name; }
    public void setName(String s_name) { this.name = s_name; }
}
