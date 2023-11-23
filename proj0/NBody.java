public class NBody {

    public static double readRadius(String filePath) {
        In in = new In(filePath);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int pNumber = in.readInt();
        in.readDouble();
        Planet[] pList = new Planet[pNumber];
        for (int i=0; i < pNumber; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String nameImg = in.readString();
            pList[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, nameImg);
        }
        return pList;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setXscale(-r, r);
        StdDraw.setYscale(-r, r);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.enableDoubleBuffering();

        for (Planet p:planets) {
            p.draw();
        }
        StdDraw.show();
        StdDraw.pause(2000);
    }
}
