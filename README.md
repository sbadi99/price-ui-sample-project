# price-ui-sample-project

* The Project is mostly written in Kotlin (with some Java).
* It uses the latest androidx framework (rather than older support libraries).
* From a UI standpoint, leveraged ConstraintLayouts for RecylerView items view. For AlertDialogs used LinearLayouts instead since ConstraintLayouts have some anomalies when used with Dialogs.

* From a architecture standpoint, used the LiveData/ViewModel approach (per Android architectural components) to allow for UI layer to be decoupled from the business layer. 

* Also used RxJava and Retrofit2 on the networking side. Used Google's EasyPermissions library to streamline management of Location permissions.

Extra's : Used Dagger2 for dependency injection in some areas. Added zip validations. Added progress spinners and some additional error dialogs as needed. Also navigating user to app level permission settings screen when user chooses to remove locations. Added a basic Espresso Instrumentation test as well.

If the team has any questions or need any clarification during the review process let me know and I will be happy to address any queries the team might have.
