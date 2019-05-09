# GunRPC-Register-Center

`
**public class BootServer {

    public static void main(String[] args)
    {
        GunBootServer server = GunBootServerFactory.getInstance();
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunStdRPCServerFilter()).
                //  addFilter(new GunHttpdHostCheck()).
                        setHandle(new GunStdRPCHandle());
        try {
            server.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}

``
this is a RPC framework that support high concurrent
you can used it as a RPC Provider ,a Register-Center
and so on.
now we support data struct include following:
int(32),byte(8),boolean(8),short(16),long(64)
string(?),int[](?),Object can seriz(?)