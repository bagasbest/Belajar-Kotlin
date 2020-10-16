package annotations

@Target(AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.FIELD)

@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Beta()

class ExampleTarget(
        //annotation beta dimasukkan ke konstruktor
        @field:Beta val fn: String,
        @get:Beta val mn: String,
        @param:Beta val ln:String) {

}