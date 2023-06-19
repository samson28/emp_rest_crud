import 'dart:convert';

import 'package:emp_front/provider/hive_provider.dart';
import 'package:emp_front/utilities/urls.dart';
import 'package:flutter/foundation.dart';
import 'package:jwt_decode_full/jwt_decode_full.dart';
import 'package:http/http.dart' as http;

class TokenProvider {
  static final TokenProvider instance = TokenProvider();
  static late String token;

  static init(String token, String refresh) async {
    HiveProvider.box.put('token', token);
    HiveProvider.box.put('refresh', refresh);
  }

  static Future<String> getToken() async {
    final jwtData = jwtDecode(HiveProvider.box.get('token'));
    DateTime exp = jwtData.expiration!;
    if (DateTime.now().compareTo(exp) == 0 ||
        DateTime.now().compareTo(exp) > 0) {
      var url = Uri.parse(uri + UserUrl().refreshUrl());
      var response = await http.post(url,
          body: {'refreshToken': HiveProvider.box.get("refresh")},
          headers: {'Accept': 'application/json'});
      Map<String, dynamic> jsonData = jsonDecode(response.body);
      if (kDebugMode) {
        print(jsonData);
        print(jsonData);
        print(jsonData);
        print(jsonData);
        print(jsonData);
        print(jsonData);
        print(jsonData);
      }

      String token = jsonData['accessToken'];

      HiveProvider.box.put('token', token);

      return HiveProvider.box.get('token');
    }
    return HiveProvider.box.get('token');
  }
}
