package data

class Node (title: String) {


    var title: String = title
        //getter
        get() = field

        //setter
        set(value) {
                if(value.isNotBlank()){
                    field = value
                }else{
                    println("Print Value")
                }

        }
}

class BigNote (val title: String) {
    val bigTitle: String
        get() = title.toUpperCase()

}