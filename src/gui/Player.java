package gui;
public class Player {
    private String team;

    public Player(String team){
        this.team = team;
    }

    public String getPlayerTeam(){
        return team;
    }

    public void setPlayerTeam(String team){
    	this.team = team;
    }
}
