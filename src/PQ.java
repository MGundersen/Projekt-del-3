/**
      _             _      __ _  _                                              _ __ _____
     | |           (_)    /_ | || |     ___                                    | /_ | ____|
   __| | __ _ _ __  _  ___ | | || |_   ( _ )    _ __ ___   __ _ _   _ _ __   __| || | |__
  / _` |/ _` | '_ \| |/ _ \| |__   _|  / _ \/\ | '_ ` _ \ / _` | | | | '_ \ / _` || |___ \
 | (_| | (_| | | | | | (_) | |  | |   | (_>  < | | | | | | (_| | |_| | | | | (_| || |___) |
  \__,_|\__,_|_| |_| |\___/|_|  |_|    \___/\/ |_| |_| |_|\__, |\__,_|_| |_|\__,_||_|____/
                  _/ |                                     __/ |
                 |__/                                     |___/
*/

public interface PQ {
    public Element extractMin();
    public void insert(Element e);
}