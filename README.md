# GunRPC
`
public class BootServer {
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
`