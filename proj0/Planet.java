public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;      /* Its current x position */
        this.yyPos = yP;      /* Its current y position */
        this.xxVel = xV;      /* Its current velocity in the x direction */
        this.yyVel = yV;      /* Its current velocity in the y direction */
        this.mass = m;        /* Its mass */
        this.imgFileName = img;
    }

    /** Creating a copy Object of P */
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /** Calculate distance of two Object */
    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /** Calculate force exert by Object p */
    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return G * this.mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double forceTotal = calcForceExertedBy(p);
        return forceTotal * (p.xxPos - this.xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double forceTotal = calcForceExertedBy(p);
        return forceTotal * (p.yyPos - this.yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] pList) {
        double result = 0;
        for (Planet p: pList) {
            if (!this.equals(p)) {
                result = result + calcForceExertedByX(p);
            }
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] pList) {
        double result = 0;
        for (Planet p: pList) {
            if (!this.equals(p)) {
                result = result + calcForceExertedByY(p);
            }
        }
        return result;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}

