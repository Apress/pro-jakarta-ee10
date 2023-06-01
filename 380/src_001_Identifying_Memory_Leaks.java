    static class A {
        int x = 7;
    }

    static List<A> list = new ArrayList<>();

    public String memLeak() {
        Runnable r = new Runnable() {
            public void run() {
                while (true) {
                    for (int i = 0; i < 500; i++) {
                        A a = new A();
                        if (Math.random() < 0.5)
                            list.add(a);
                    }
                    try {
                        Thread.sleep(0, 100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        new Thread(r).start();
        return "{\"result\":\"" + "OK" + "\"}";
    }
