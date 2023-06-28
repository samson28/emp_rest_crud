import 'package:bloc/bloc.dart';
import 'package:emp_front/repository/user_repository.dart';
import 'package:meta/meta.dart';

part 'sign_in_event.dart';
part 'sign_in_state.dart';

class SignInBloc extends Bloc<SignInEvent, SignInState> {
  UserRepo userRepo = UserRepo();
  SignInBloc() : super(const SignInInitial()) {
    on<SignedInEvent>((event, emit) async {
      String message = await userRepo.login(event.username, event.password);
      if (message == 'success') {
        emit(SignedInState(username: event.username, password: event.password));
      } else {
        emit(SignInErrorState(errorMessage: message));
      }
    });
  }
}
