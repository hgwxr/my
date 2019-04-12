package com.hgwxr.rxjava.myrxjava;

public class Test {

    public static void main(String[] args) {
        Observable.create(new Observable.ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Observable.Observer<String> e) throws Exception {
                e.onNext("hello world");
            }
        }).map(new Observable.Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return "map" + s;
            }
        }).subscribe(new Observable.Observer<String>() {
            @Override
            public void onSubscribe() {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String value) {

                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

                System.out.println(e);
            }

            @Override
            public void onComplete() {

                System.out.println("onComplete");
            }
        });
    }
}
