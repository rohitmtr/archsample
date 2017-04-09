# Architecture Sample

  This is sample code for organising the code or architecure of the app that we are following here. 
  We are are following Unnamed Architecture pattern.

Code is mainly divide into three part 
### Data Layer
   - Mainly contains data repository and will expose the Obserable 

### Domain Layer
   - Mainly contains business logic and will expose the  Obserable

### Presenter Layer 
   - this layer will get the data from Domain layer 
     by subscribing to particular use case and reponsible for subscirbing and unsubscribing from data source.

## [Dagger 2](https://google.github.io/dagger/)
We are using dagger 2 for DI library to decouple the code dependency and making more testable and trying to use construction injection 
as much as possible so that we can add more dependency without changing the code except the constructor if that dependency is provided by any parent compenent
Some tips:
```sh
Use @Binds as much as possible.
Use static provider if Module don't have parameterised constructor.
```

There is one [AppComponent](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/di/AppComponent.java) 
and All Activity component will the subcomponent of this component similarly All Fragment component will subcomonent of Activity compenent 
and for Custom view can be subcomponent of activity or fragment only.

So how it works ?

Any Global dependency that is singleton to app like Sharedprefecnce, OkHttp Client etc will be provide by AppComponent 
and will be available to all subcompenent. So any subcomponent do not need add module or provide for those depenency.
similarly if two or more fragments (belong to same activity) depend on one common dependency then this dependency will be provided by Activity Subcomponent.

```AppComponent ----one to many----->   ActivitySubcomponents --- one to many --> FragmentSubcomponents```

We will soon start using [AndoidInjection](https://google.github.io/dagger/android.html) once its finalise by dagger 2. currenlty its in beta phase.


## [RxJava 2](https://github.com/ReactiveX/RxJava/wiki)
We are using RxJava for streams and data flow. So Data repository expose data stream as Flowable 
so that Interactors can subcribe to those stream and modify data as per business logic and expose further data stream as Flowable.
Interactor data stream will be used by presenter.

we are using [Trello](https://github.com/trello/RxLifecycle) library as android lifecyle events stream of RxJava.

We are using [RxBinder](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/rx/RxBinder.java) interface implemented by RxActivity and RxFragment to compose Observable with lifecycle 
and also specify the observeOn and subscribeOn schedulers.


## Sample Code Detail

We are breaking every single View into small independent unit and following Presenter architcture for exmaple if One Activity or Fragment have one cusotmview then that customview will have its own presenter and will work independently
So by that one activity or fragment can implement multiple views and will have multiple presenters.

In This sample code there is one screen with left navigation drawer 

And there are two repository [User Repository](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/repository/UserRepository.java) and [User Posts Repository](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/repository/PostRepository.java), 
[User Repository](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/repository/UserRepository.java) handle all user stream and set and get selelcted user
and [User Posts Repository](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/repository/PostRepository.java) expose the stream for posts of selected user.

There are three use cases [UserInteractor](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/domain/user/UserInteractor.java), [SetGetUserInteractor](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/domain/user/SetGetUserInteractor.java) and [PostsInteractor](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/domain/post/PostsInteractor.java) 

Left Navigation Drawer have all User list implemented by CustomView Appoarch. and subscire to [UserInteractor](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/domain/user/UserInteractor.java) and show all user in left navigation bar
and when user select any user then it set that user into [SetGetUserInteractor](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/domain/user/SetGetUserInteractor.java).

[HomeFragment](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/home/HomeFragment.java) in [MainActivity](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/home/MainActivity.java)
Subscibe to [SelectedUserPostsInteractor](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/domain/post/SelectedUserPostsInteractor.java) so when user change it fetch data from [PostRepository](https://github.com/rohitmtr/archsample/blob/master/app/src/main/java/com/sample/arch/repository/PostRepository.java).























  
  
