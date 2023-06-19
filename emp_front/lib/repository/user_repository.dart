import 'dart:convert';

import 'package:emp_front/model/user.dart';
import 'package:emp_front/provider/hive_provider.dart';
import 'package:emp_front/provider/token_provider.dart';
import 'package:emp_front/utilities/urls.dart';
import 'package:http/http.dart' as http;

class UserRepo {
  login(String username, String password) async {
    var url = Uri.parse(uri + UserUrl().loginUrl());
    var response = await http.post(url,
        body: {'username': username, 'password': password},
        headers: {'Accept': 'application/json'});
    Map<String, dynamic> jsonData = jsonDecode(response.body);

    if (jsonData['status'] == 'success') {
      String token = jsonData['access_token'];
      String refresh = jsonData['refresh_token'];
      TokenProvider.init(token, refresh);
      return 'success';
    } else {
      if (jsonData['message'] == 'Unauthorized') {
        return 'Identifiants Incorrectes';
      }
      return jsonData['message'];
    }
  }

  register(User user) async {
    var url = Uri.parse(uri + UserUrl().registerUrl());
    var response = await http.post(url,
        body: user.toJson(), headers: {'Accept': 'application/json'});
    Map<String, dynamic> jsonData = jsonDecode(response.body);

    if (jsonData['status'] == 'success') {
      HiveProvider.box.put('token', jsonData['authorisation']['access_token']);
      return 'success';
    } else {
      return jsonData['message'];
    }
  }

  logout() async {
    var url = Uri.parse(uri + UserUrl().logoutUrl());
    var token = TokenProvider.getToken();
    var response = await http.post(url, headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer $token'
    });
    final jsonData = jsonDecode(response.body);
    return jsonData;
  }

  current() async {
    var url = Uri.parse(uri + UserUrl().currentUrl());
    var token = TokenProvider.getToken();
    var response = await http.get(url, headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer $token'
    });
    final jsonData = jsonDecode(response.body);
    return jsonData;
  }

  refresh() async {
    var url = Uri.https(uri, UserUrl().refreshUrl());
    var token = TokenProvider.getToken();
    var response = await http.get(url, headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer $token'
    });
    final jsonData = jsonDecode(response.body);
    return jsonData;
  }
}
