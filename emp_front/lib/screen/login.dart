import 'package:emp_front/repository/user_repository.dart';
import 'package:emp_front/screen/employe_list.dart';
import 'package:emp_front/screen/register.dart';
import 'package:flutter/material.dart';

class Login extends StatefulWidget {
  const Login({Key? key}) : super(key: key);

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {
  final loginFormkey = GlobalKey<FormState>();
  final _email = TextEditingController();

  final _password = TextEditingController();

  @override
  void dispose() {
    _email.dispose();
    _password.dispose();
    super.dispose();
  }

  home() {
    Navigator.pushAndRemoveUntil(
        context,
        MaterialPageRoute<void>(
            builder: (BuildContext context) => const EmployeList()),
        (route) => false);
  }

  @override
  Widget build(BuildContext context) {
    Future<void> login() async {
      var x = await UserRepo().login(_email.value.text, _password.value.text);
      if (x == 'success') {
        showDialog(
            context: context,
            barrierDismissible: false,
            builder: (context) => const Center(
                  child: CircularProgressIndicator(),
                ));
        home();
      } else {
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text(x)));
      }
    }

    return Scaffold(
      body: ListView(children: [
        SizedBox(
          width: MediaQuery.of(context).size.width,
          height: MediaQuery.of(context).size.height,
          child: Center(
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Form(
                key: loginFormkey,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    const Icon(
                      Icons.login_sharp,
                      size: 100,
                    ),
                    const SizedBox(
                      height: 35,
                    ),
                    TextFormField(
                        controller: _email,
                        keyboardType: TextInputType.emailAddress,
                        decoration: const InputDecoration(
                          border: OutlineInputBorder(),
                          labelText: 'Email',
                        ),
                        validator: (value) {
                          if (value!.isEmpty) {
                            return 'Please enter you E-mail';
                          }
                          return null;
                        }),
                    const SizedBox(
                      height: 15,
                    ),
                    TextFormField(
                        controller: _password,
                        obscureText: true,
                        decoration: const InputDecoration(
                          border: OutlineInputBorder(),
                          labelText: 'Password',
                        ),
                        validator: (value) {
                          if (value!.isEmpty) {
                            return 'Please enter your Password';
                          }
                          return null;
                        }),
                    const SizedBox(
                      height: 15,
                    ),
                    GestureDetector(
                      onTap: () {
                        if (loginFormkey.currentState!.validate()) {
                          login();
                        }
                      },
                      child: Container(
                        height: 50.0,
                        padding: const EdgeInsets.all(8.0),
                        margin: const EdgeInsets.symmetric(horizontal: 8.0),
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(5.0),
                          color: Colors.lightBlue[500],
                        ),
                        child: const Center(
                          child: Text('Log In'),
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 5,
                    ),
                    TextButton(
                      onPressed: () {
                        Navigator.pushAndRemoveUntil(
                            context,
                            MaterialPageRoute<void>(
                                builder: (BuildContext context) =>
                                    const Register()),
                            (route) => false);
                      },
                      child: const Text("You Don't Have Any Account ?"),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ),
      ]),
    );
  }
}
