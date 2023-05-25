package com.sundroid.sundroid.practice
















fun carPrice(old: Int=5, kilometers: Int=10000, maximumSpeed: Int=120, automatic: Boolean= false){
    var price: Int = 20000
    price -= ((old - 5) * 2000)
    price -= ((120 - maximumSpeed) * 100)
    price -= ((kilometers - 100000) / 10000) * 200
    if(automatic){
        price += 1500
    }
    println(price)

}
fun main(){
    carPrice(old = 5, kilometers = 120000, maximumSpeed = 120, automatic = false)
}