import 'package:bloc/bloc.dart';
import 'package:emp_front/model/user.dart';
import 'package:emp_front/repository/user_repository.dart';
import 'package:meta/meta.dart';

part 'sign_up_event.dart';
part 'sign_up_state.dart';

class SignUpBloc extends Bloc<SignUpEvent, SignUpState> {
  UserRepo userRepo = UserRepo();
  SignUpBloc() : super( SignUpInitial()) {
     on<SignedUpEvent>((event, emit) async {
      String message = await userRepo.register(event.user);
      if (message == 'success') {
        emit(SignedUpState(user: event.user));
      } else {
        emit(SignUpErrorState(errorMessage: message));
      }
    });
  }
}
