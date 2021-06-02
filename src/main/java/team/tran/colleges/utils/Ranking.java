package team.tran.colleges.utils;

/**
 * @desc 热榜精品姓名
 */
public enum Ranking {

    HOTCOURSE{
        public String getName(){
            return "HOTCOURSE";
        }
    },BOUTIQUE{
        public String getName(){
            return "BOUTIQUE";
        }
    };

    public abstract String getName();

}
