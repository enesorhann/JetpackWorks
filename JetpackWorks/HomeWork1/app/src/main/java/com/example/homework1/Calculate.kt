package com.example.homework1

import java.util.Scanner

fun main(){
    var option:Int = 0
    var scanner = Scanner(System.`in`)

    while (true){
        print("Lutfen bir sayi giriniz: ")
        option = scanner.nextInt()

        if (option==0){
            println("000000000 $option")
        }else if (option>0){
            println("Pozitif Sayi $option")
        }else{
            println("Negatif Sayi $option")
        }
    }




}