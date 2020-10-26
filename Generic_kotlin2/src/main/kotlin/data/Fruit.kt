package data

class Fruit(val name: String, val qty: Int): Comparable<Fruit> {
    override fun compareTo(other: Fruit): Int {
        return qty.compareTo(other.qty)
    }

}