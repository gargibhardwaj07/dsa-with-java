class Robot {
   
    private final int w, h, perim;
    private int cur;
    private boolean stepped;

    public Robot(int width, int height) {
        w = width; h = height;
        perim = 2*(w-1) + 2*(h-1);
        cur = 0; stepped = false;
    }

    public void step(int num) {
        stepped = true;
        cur = (cur + num) % perim;
    }

    public int[] getPos() { return toPos(cur); }

    public String getDir() {
        if (!stepped || cur == 0) return stepped ? "South" : "East";
        return toDir(cur);
    }

    private int[] toPos(int i) {
        if (i <= w-1)  return new int[]{i, 0};           // East: x=i, y=0
        i -= w;
        if (i <= h-2)  return new int[]{w-1, i+1};       // North: x=w-1, y=1..h-1
        i -= (h-1);
        if (i <= w-2)  return new int[]{w-2-i, h-1};     // West: x=w-2..0, y=h-1
        i -= (w-1);
                       return new int[]{0, h-2-i};        // South: x=0, y=h-2..1
    }

    private String toDir(int i) {
        if (i <= w-1)  return "East";
        i -= w;
        if (i <= h-2)  return "North";
        i -= (h-1);
        if (i <= w-2)  return "West";
                       return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */