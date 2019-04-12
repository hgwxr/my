package com.hgwxr.rxjava.myrxjava;


public class Observable<T> {

    private final ObservableOnSubscribe<T> mOnSubscribe;

    private Observable(ObservableOnSubscribe<T> onSubscribe) {
        mOnSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }

    public <R> Observable<R> map(Function<T, R> function) {
        return create(new ObservableMap<>(this, function));
//        return mapMethod1(function);
    }

    private <R> Observable<R> mapMethod1(Function<T, R> function) {
        return create(new ObservableOnSubscribe<R>() {
            @Override
            public void subscribe(Observer<R> observer) throws Exception {
                Observable.this.subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe() {
//                        observer.onSubscribe();
                    }

                    @Override
                    public void onNext(T value) {
                        try {
                            observer.onNext(function.apply(value));
                        } catch (Exception e) {
                            observer.onError(e);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(e);
                    }

                    @Override
                    public void onComplete() {
//                        observer.onComplete();
                    }
                });
            }
        });
    }

    public void subscribe(Observer<T> observer) {
        observer.onSubscribe();
        try {
            mOnSubscribe.subscribe(observer);
        } catch (Exception e) {
            observer.onError(e);
        } finally {
            observer.onComplete();
        }
    }

    public interface Function<T, R> {

        R apply(T t) throws Exception;
    }

    public interface Observer<T> {


        void onSubscribe();


        void onNext(T value);


        void onError(Throwable e);

        void onComplete();

    }

    public interface ObservableOnSubscribe<T> {
        void subscribe(Observer<T> e) throws Exception;
    }

    public static class ObservableMap<T, R> implements ObservableOnSubscribe<R>, Observer<T> {

        private final Observable<T> mSource;
        private final Function<T, R> mFunction;
        private Observer<R> observer;

        public ObservableMap(Observable<T> source, Function<T, R> function) {
            mSource = source;
            mFunction = function;
        }


        @Override
        public void subscribe(Observer<R> observer) throws Exception {
            this.observer = observer;
            mSource.subscribe(this);
        }

        @Override
        public void onSubscribe() {
//                        observer.onSubscribe();
        }

        @Override
        public void onNext(T value) {
            try {
                observer.onNext(mFunction.apply(value));
            } catch (Exception e) {
                observer.onError(e);
            }
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
//                        observer.onComplete();
        }
    }
}
