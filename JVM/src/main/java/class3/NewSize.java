package class3;

public class NewSize {

    //-Xms20M -Xmx20M -Xmn2M -XX:+PrintGCDetails -XX:SurvivorRatio=2

    /**
     * [GC (Allocation Failure) [PSYoungGen: 1024K->504K(1536K)] 1024K->608K(19968K), 0.0007717 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * Heap
     *  PSYoungGen      total 1536K, used 1103K [0x00000000ffe00000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 1024K, 58% used [0x00000000ffe00000,0x00000000ffe95f40,0x00000000fff00000)
     *   from space 512K, 98% used [0x00000000fff00000,0x00000000fff7e010,0x00000000fff80000)
     *   to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
     *  ParOldGen       total 18432K, used 10344K [0x00000000fec00000, 0x00000000ffe00000, 0x00000000ffe00000)
     *   object space 18432K, 56% used [0x00000000fec00000,0x00000000ff61a0d0,0x00000000ffe00000)
     *  Metaspace       used 3202K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 347K, capacity 388K, committed 512K, reserved 1048576K
     *
     *
     *
     */


    //-Xms20M -Xmx20M -Xmn7M -XX:+PrintGCDetails -XX:SurvivorRatio=2

    /**
     * [GC (Allocation Failure) [PSYoungGen: 3655K->1512K(5632K)] 3655K->2740K(18944K), 0.0018842 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 4661K->1520K(5632K)] 5889K->5876K(18944K), 0.0025682 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 4661K->1528K(5632K)] 9018K->8972K(18944K), 0.0018300 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * Heap
     *  PSYoungGen      total 5632K, used 3731K [0x00000000ff900000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 4096K, 53% used [0x00000000ff900000,0x00000000ffb26ee8,0x00000000ffd00000)
     *   from space 1536K, 99% used [0x00000000ffd00000,0x00000000ffe7e040,0x00000000ffe80000)
     *   to   space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
     *  ParOldGen       total 13312K, used 7444K [0x00000000fec00000, 0x00000000ff900000, 0x00000000ff900000)
     *   object space 13312K, 55% used [0x00000000fec00000,0x00000000ff345080,0x00000000ff900000)
     *  Metaspace       used 3202K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 347K, capacity 388K, committed 512K, reserved 1048576K
     *
     *
     */

    //-Xms20M -Xmx20M -Xmn15M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    /**
     * Heap
     *  PSYoungGen      total 13824K, used 12288K [0x00000000ff100000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 12288K, 100% used [0x00000000ff100000,0x00000000ffd00000,0x00000000ffd00000)
     *   from space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
     *   to   space 1536K, 0% used [0x00000000ffd00000,0x00000000ffd00000,0x00000000ffe80000)
     *  ParOldGen       total 5120K, used 0K [0x00000000fec00000, 0x00000000ff100000, 0x00000000ff100000)
     *   object space 5120K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff100000)
     *  Metaspace       used 3201K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 347K, capacity 388K, committed 512K, reserved 1048576K
     */

    //-Xms20M -Xmx20M -XX:+PrintGCDetails -XX:NewRatio=2
    /**
     *
     * [GC (Allocation Failure) [PSYoungGen: 4839K->488K(6144K)] 4839K->3780K(19968K), 0.0019704 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 5715K->504K(6144K)] 9007K->8948K(19968K), 0.0028369 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Ergonomics) [PSYoungGen: 504K->0K(6144K)] [ParOldGen: 8444K->8811K(13824K)] 8948K->8811K(19968K), [Metaspace: 3195K->3195K(1056768K)], 0.0062162 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
     * Heap
     *  PSYoungGen      total 6144K, used 2374K [0x00000000ff980000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 5632K, 42% used [0x00000000ff980000,0x00000000ffbd1930,0x00000000fff00000)
     *   from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
     *   to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
     *  ParOldGen       total 13824K, used 8811K [0x00000000fec00000, 0x00000000ff980000, 0x00000000ff980000)
     *   object space 13824K, 63% used [0x00000000fec00000,0x00000000ff49ac78,0x00000000ff980000)
     *  Metaspace       used 3201K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 347K, capacity 388K, committed 512K, reserved 1048576K
     *
     * Process finished with exit code 0
     *
     */
    public static void main(String[] args) {
        int cap = 2 * 1024 * 1024;
        byte[] b1 = new byte[cap];
        byte[] b2 = new byte[cap];
        byte[] b3 = new byte[cap];
        byte[] b4 = new byte[cap];
        byte[] b5 = new byte[cap];
        byte[] b6 = new byte[cap];
//        byte[] b7 = new byte[cap];
//        byte[] b8 = new byte[cap];
//        byte[] b9 = new byte[cap];
//        byte[] b10 = new byte[cap];

    }

}
