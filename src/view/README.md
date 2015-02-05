Author: Camille TIENNOT (camille.tiennot[at]telecom-paristech.fr)

View Package
============

This package provides classes to display the information to the user using the
processing API. The first class to be called should be MainWindow. When a
MainWindow object is created, it must receive a PApplet parameter as a
parameter of its constructor. this PApplet parameter is then kept in the 
attributes of the MainWindow object. The MainWindow object passes its 
PApplet attribute as a parameter to the constructors of the objects it
instanciates, and so on.

