import java.io.*;
import java.util.StringTokenizer;

/*
TASK: fence4
LANG: JAVA
 */
public class fence4 {
    public static int points;
    public static int MAXN = 201;
    public static double obsX, obsY;
    public static double[][] coordinates = new double[MAXN][2];
    public static int[] canSee = new int[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence4.out")));
        points = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        obsX = Double.parseDouble(st.nextToken());
        obsY = Double.parseDouble(st.nextToken());
        for (int i = 0; i < points; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            coordinates[i][0] = x;
            coordinates[i][1] = y;
        }
        coordinates[points][0] = coordinates[0][0];
        coordinates[points][1] = coordinates[0][1];

        /*
        We can reasonably infer that if a pair of segments doesn't share a vertex but has an intersection cannot make up a polygon

         */
        /* for each pair of segments that don't share a vertex */
        for (int i = 0; i < points; i++)
            for (int j = i + 2; j < points; j++)
                if (check_intersect(i, j) > 0) { /* if they intersect */

                    /* and don't share a vertex */
                    if (i == 0 && j == points - 1) {
                        continue;
                    }

                    /* then the fence is invalid */
                    pw.println("NOFENCE");
                    pw.close();
                    System.exit(0);

                }
        for (int i = 0; i < points; i++) {
            /* check endpoint */
            canSee[first_inter(obsX, obsY, coordinates[i][0], coordinates[i][1])] = 1;

            /* check midpoint of segment (lv, lv+1) */
            canSee[first_inter(obsX, obsY, (coordinates[i][0] + coordinates[i + 1][0]) * 0.5, (coordinates[i][1] + coordinates[i + 1][1]) * 0.5)] = 1;
        }
        int cnt = 0;
        for (int i = 0; i < points; i++) {
            if (canSee[i] > 0) {
                cnt++;
            }
        }

        pw.println(cnt);

        /* list visible segments */
        for (int i = 0; i < points - 2; i++) {
            if (canSee[i] > 0) {
                pw.println((int) coordinates[i][0] + " " + (int) coordinates[i][1] + " " + (int) coordinates[i + 1][0] + " " + (int) coordinates[i + 1][1]);
            }
        }
        if (canSee[points - 1] > 0) {
            pw.println((int) coordinates[0][0] + " " + (int) coordinates[0][1] + " " + (int) coordinates[points - 1][0] + " " + (int) coordinates[points - 1][1]);
        }
        if (canSee[points - 2] > 0) {
            pw.println((int) coordinates[points - 2][0] + " " + (int) coordinates[points - 2][1] + " " + (int) coordinates[points - 2 + 1][0] + " " + (int) coordinates[points - 2 + 1][1]);
        }
        pw.close();

    }


    public static int check_intersect(int f1, int f2) { /* do (f1,f1+1) and (f2,f2+1) intersect? */
        double sx, sy;
        double ex, ey;

        sx = coordinates[f1][0];
        sy = coordinates[f1][1];
        ex = coordinates[f1 + 1][0];
        ey = coordinates[f1 + 1][1];

        if (side(sx, sy, ex, ey, f2) == side(sx, sy, ex, ey, f2 + 1)) {
            /* are the f2 and f2+1 on the same side of (f1,f1+1)? */
            return 0; /* if so, the segments don't intersect */
        }

        sx = coordinates[f2][0];
        sy = coordinates[f2][1];
        ex = coordinates[f2 + 1][0];
        ey = coordinates[f2 + 1][1];

        if (side(sx, sy, ex, ey, f1) == side(sx, sy, ex, ey, f1 + 1)) {
            /* are f1 & f1+1 on the same side of (f2,f2+1) */
            return 0; /* if so, the segments don't intersect */
        }

     /* the endpoints of each segment are on opposite sides of
     the other segment.  Therefore, they intersect */
        return 1;
    }

    public static int side(double sx, double sy, double ex, double ey, int p) {
        /* determine the side that the points lie on */
        double dx, dy;
        double px, py;
        double t;

        dx = ex - sx;
        dy = ey - sy;

        px = coordinates[p][0] - sx;
        py = coordinates[p][1] - sy;

        /* take cross-product */
        t = dx * py - dy * px;

        if (t > 0.00001) return 0; /* "left" side */
        if (t < -0.00001) return 1; /* "right" side */
        return 2; /* on the line */
    }

    public static int first_inter(double sx, double sy, double ex, double ey) { /* what is the first segment intersected by the ray s->e */
        int lv; /* loop variable */
        int t1, t2;
        int s1, s2;
        double ax, ay, bx, by;
        double t;
        double coeff, cnst;
        double i, j;
        double x, y;
        double mlbrush, mrbrush; /* when is the earliest brush on a side? */

        /* min = distance to nearest intersection point */
        /* mloc = edge where this occurs */
        double min = 1e10; /* ~= infinity */
        int mloc = points; /* unused location */

        mlbrush = mrbrush = 1e10; /* infinity */

        for (lv = 0; lv < points; lv++) { /* for each segment, determine length along */
            ax = coordinates[lv][0];
            ay = coordinates[lv][1];
            bx = coordinates[lv + 1][0];
            by = coordinates[lv + 1][1];

            /* take cross product */
            t = (ex - sx) * (ay - by) - (ey - sy) * (ax - bx);
            if (t > -0.00001 && t < 0.00001) /* parallel */
                continue; /* not considered visible */

            /* not parallel */
    /* we are now solving the following equations:
     (ex - sx) j + sx = (bx - ax) i + ax
     (ey - sy) j + sy = (by - ay) i + ay
    */

    /* solves for alpha by multiple first by (by - ay) and
       the second by (bx - ax) and subtracting equations */
            cnst = (ax - sx) * (by - ay) - (ay - sy) * (bx - ax);
            coeff = (ex - sx) * (by - ay) - (ey - sy) * (bx - ax);
            if (coeff > -0.00001 && coeff < .00001) { /* degenerate, one of bx - ax and by - ay is about zero */
                if (bx - ax > -.00001 && bx - ax < 0.00001) { /* bx - ax == 0, can solve first eqn directly */
                    cnst = ax - sx;
                    coeff = ex - sx;
                } else { /* by - ay == 0, can solve second eqn directly */
                    cnst = ay - sy;
                    coeff = ey - sy;
                }
            }
            j = cnst / coeff;

            /* if intersection occurs before starting point, no intersection */
            if (j < -.00001) continue;

            /* determine beta */
            cnst = sx + (ex - sx) * j - ax;
            coeff = bx - ax;
            if (coeff > -0.00001 && coeff < .00001) { /* handle degeneracy */
                cnst = sy + (ey - sy) * j - ay;
                coeff = by - ay;
            }
            i = cnst / coeff;

    /* if the interesection occurs with i < 0 | i > 1, the
       intersection is not within the confines of the segment */
            if (i < -.00001 || i > 1.00001) continue;

            /* calculate intersection point */
            x = ax + (bx - ax) * i;
            y = ay + (by - ay) * i;

            /* determine distance along line that intersection occurs */
            t = (x - sx) * (ex - sx) + (y - sy) * (ey - sy);

            /* make sure it's in bounds, and better than what we have */
            if (t < -0.00001 || t > min) continue;

            /* if it occurs at an end point */
            if (i < .00001 || i > 0.99999) {
                /* find the endpoints that are incident to the intersected endpoint */
                if (i < .00001) {
                    t1 = lv - 1;
                    if (t1 < 0) t1 += points;
                    t2 = lv + 1;
                } else {
                    t1 = lv;
                    t2 = lv + 2;
                    if (t2 >= points) t2 -= points;
                }

      /* if they lie on the same side of the line, then ray 'brushes'
         endpoint, which is not considered to an intersection */

                s1 = side(sx, sy, ex, ey, t1);
                s2 = side(sx, sy, ex, ey, t2);
                if (s1 == s2) {
                    if (s1 == 0 && t < mlbrush) mlbrush = t;
                    if (s1 == 1 && t < mrbrush) mrbrush = t;
                    continue;
                }
            }
            /* found a better edge! */
            min = t;
            mloc = lv;
        }
        /* if it brushes on both sides, it cannot be seen */
        if (min > mlbrush && min > mrbrush) return points;
        return mloc;
    }

}

/*

 */
