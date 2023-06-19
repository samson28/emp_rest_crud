import 'package:emp_front/screen/employe_form_screen.dart';
import 'package:emp_front/screen/employe_list.dart';
import 'package:emp_front/screen/login.dart';
import 'package:emp_front/screen/register.dart';
import 'package:flutter/material.dart';

class RouteGenerator {
  static Route<dynamic> generateRoute(RouteSettings settings) {
    switch (settings.name) {
      case '/':
        return MaterialPageRoute(builder: (context) => const Login());
      case '/register':
        return MaterialPageRoute(builder: (context) => const Register());
      case '/home':
        return MaterialPageRoute(builder: (context) => const EmployeList());
      case '/addEmp':
        return MaterialPageRoute(
            builder: (context) => const EmployeFormScreen());

      default:
        return MaterialPageRoute(
            builder: (context) => Scaffold(
                  appBar: AppBar(
                    title: const Text("OUPS"),
                    centerTitle: true,
                  ),
                  body: const Center(
                    child: Text("Comming Soon ..."),
                  ),
                ));
    }
  }
}
