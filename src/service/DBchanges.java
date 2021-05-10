package service;

import entity.*;

public interface DBchanges {
    int addquestion(question question);
    int addrules(rules rules);
    int addtestpaper(testpaper testpaper);
    int delquestion(question question);
    int delrules(rules rules);
    int deltestpaper();
    int changequestion(question question);
    int changetestpaper();

}
