const String uri = "http://192.168.1.65:8080/api/";

class UserUrl {
  String loginUrl() => "login";
  String registerUrl() => "user/store";
  String logoutUrl() => "logout";
  String refreshUrl() => "refresh";
  String currentUrl() => "current";
}

class EmployeUrl {
  String getUrl() => "employee/all";
  String addUrl() => "employee/store";
  String getOneUrl(id) => "employee/show/$id";
  String updateUrl(id) => "employee/update/$id";
  String deleteUrl(id) => "employee/delete/$id";
  String searchUrl(keyword) => "employee/search/$keyword";
}
