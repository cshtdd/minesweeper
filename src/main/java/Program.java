import com.tddapps.io.Tty;

public class Program {
    public static void main(String[] args){
        var io = new Tty();

        io.display("Minesweeper");
        io.display("Continue?");
        var a = io.read();

        if (a.toLowerCase().contains("y")){
            io.display("Continue");
        }
    }
}
