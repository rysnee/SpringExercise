package vn.elca.training.model.exception;

public class ProjectNotFoundException extends Exception {
    String keyword;
    long id;

    public ProjectNotFoundException(String msg, long id){
        super(msg);
        this.id = id;
    }

    public ProjectNotFoundException(String msg, String keyword){
        super(msg);
        this.keyword = keyword;
    }
}
