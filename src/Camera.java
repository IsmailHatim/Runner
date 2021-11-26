
public class Camera {
    private final double k_m = 1;
    private final double f_m = 2;
    protected double x;
    protected double y;
    protected double vx;
    protected double vy;
    protected double ax;
    protected double ay;

    public Camera(double x, double y, double vx, double vy, double ax, double ay){
        this.ax = ax;
        this.ay =ay;
        this.vx = vx;
        this.vy = vy;
        this.x = x;
        this.y = y;
    }

    public void update(long time, double xHero){
        ax = k_m*(xHero - x) - f_m*vx;
        ay = 0;


        vx += ax*0.016;
        vy += ay*0.016;

        x += vx*0.016;
        y += vy*0.016;
    }

    //Getter et Setter -----
    public double getX(){return x;}
    public double getY(){return y;}
    public double getVx(){return vx;}
    public double getVy(){return vy;}
    public double getAx(){return ax;}
    public double getAy(){return ay;}

    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public void setVx(double vx){this.vx = vx;}
    public void setVy(double vy){this.vy = vy;}
    public void setAx(double ax){this.ax = ax;}
    public void setAy(double ay){this.ay = ay;}

    @Override
    public String toString(){return "Caméra, upper left : "+ x +"," + y;}
}
