import 'dart:convert';

import 'package:emp_front/model/employee.dart';
import 'package:emp_front/provider/token_provider.dart';
import 'package:emp_front/utilities/urls.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

class EmployeRepo {
  addEmploye(Employe employe) async {
    var url = Uri.parse(uri + EmployeUrl().addUrl());
    var token = await TokenProvider.getToken();
    var response = await http.post(url,
        body: jsonEncode(employe.toJson()),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $token'
        });

    if (response.statusCode == 200) {
      return 'ok';
    }

    return 'no';
  }

  getEmploye() async {
    var url = Uri.parse(uri + EmployeUrl().getUrl());
    var token = await TokenProvider.getToken();
    var response = await http.get(url, headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer $token'
    });
    if (response.body == "[]") {
      List<Employe> listDesEmploye = [];
      return listDesEmploye;
    }
    var jsonData = jsonDecode(response.body);
    if (kDebugMode) {
      print(jsonData);
    }

    var list = jsonData as List<dynamic>;
    List<Employe> listDesEmploye =
        list.map((dynamic i) => Employe.fromJson(i)).toList();

    return listDesEmploye;
  }

  getOneEmploye(Employe employe) async {
    var url = Uri.parse(uri + EmployeUrl().getOneUrl(employe.id));
    var token = await TokenProvider.getToken();
    var response = await http.get(url, headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer $token'
    });
    Map<String, dynamic> jsonData = jsonDecode(response.body);
    return Employe.fromJson(jsonData);
  }

  shearchEmploye(String keyword) async {
    var url = Uri.parse(uri + EmployeUrl().searchUrl(keyword));
    var token = await TokenProvider.getToken();
    var response = await http.get(url, headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer $token'
    });
    var jsonData = jsonDecode(response.body);

    var list = jsonData as List<dynamic>;
    List<Employe> listDesEmploye =
        list.map((dynamic i) => Employe.fromJson(i)).toList();

    return listDesEmploye;
  }

  updateEmploye(Employe employe) async {
    var url = Uri.parse(uri + EmployeUrl().updateUrl(employe.id));
    var token = await TokenProvider.getToken();

    var response = await http.put(url,
        body: jsonEncode(employe.toJson()),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $token'
        });

    if (response.statusCode == 200) {
      return 'ok';
    }

    return 'no';
  }

  deleteEmploye(Employe employe) async {
    var url = Uri.parse(uri + EmployeUrl().deleteUrl(employe.id));
    var token = await TokenProvider.getToken();
    var response = await http.delete(url, headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer $token'
    });
    if (response.statusCode == 200) {
      return 'ok';
    }

    return 'no';
  }
}
