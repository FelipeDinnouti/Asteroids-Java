package src;

public class GeometryMath {
    // https://bryceboe.com/2006/10/23/line-segment-intersection-algorithm/
    // A delightfully elegant solution to checking intersection between lines
    // By defining ccw (counter clock wise) as determining if the slope of AB is less than the slope of AC, then the three points are listed in clockwise order
    private static boolean ccw(Vector a, Vector b, Vector c) {
        return (c.y-a.y)*(b.x-a.x) > (b.y-a.y)*(c.x-a.x);
    }
    public static boolean line_intersect(Vector a, Vector b, Vector c, Vector d) {
        return ccw(a,c,d) != ccw(b,c,d) && ccw(a,b,c) != ccw(a,b,d);
    }

    public static boolean aabb(Vector ra, Vector rb, Vector c) {
        // Make sure vector A is smaller than vector B, by swapping
        if (ra.x>rb.x) {
            ra.x = ra.x + rb.x; 
            rb.x = ra.x - rb.x; 
            ra.x = ra.x - rb.x; 
        }
        if (ra.y>rb.y) {
            ra.y = ra.y + rb.y; 
            rb.y = ra.y - rb.y; 
            ra.y = ra.y - rb.y; 
        } 

        if (((c.x>ra.x) && (c.x<rb.x)) &&  ((c.y>ra.y) && (c.y<rb.y))) return true;
        return false;
    }
}
