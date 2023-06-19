import 'package:hive/hive.dart';
import 'package:path_provider/path_provider.dart';

class HiveProvider {
  static final HiveProvider instance = HiveProvider();
  static late Box box;

  static init() async {
    final dir = await getApplicationDocumentsDirectory();
    Hive.init(dir.path);
    box = await Hive.openBox('myBox');
  }
}
