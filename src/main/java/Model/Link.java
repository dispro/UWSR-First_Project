package Model;

public class Link {

    private int _id;
    private String _Title;
    private String _Ref;
    private String _DescriptionLink;
    private int _IsUseful;
    private int _IsUseless;

    public Link(){}

    public Link(int id, String title, String ref, String descriptionLink, int isuseful, int isuseless){
        this._id = id;
        this._DescriptionLink = descriptionLink;
        this._IsUseful = isuseful;
        this._IsUseless = isuseless;
        this._Title = title;
        this._Ref = ref;
    }

    public Link(int id, String title, String ref, String descriptionLink){
        this._id = id;
        this._DescriptionLink = descriptionLink;
        this._IsUseful = 0;
        this._IsUseless = 0;
        this._Title = title;
        this._Ref = ref;
    }

    public int get_id() {
        return _id;
    }

    public String get_Title() {
        return _Title;
    }

    public String get_Ref() {
        return _Ref;
    }

    public String get_DescriptionLink() {
        return _DescriptionLink;
    }

    public int get_IsUseful() {
        return _IsUseful;
    }

    public int get_IsUseless() {
        return _IsUseless;
    }

    public void set_Title(String _Title) {
        this._Title = _Title;
    }

    public void set_Ref(String _Ref) {
        this._Ref = _Ref;
    }

    public void set_DescriptionLink(String _DescriptionLink) {
        this._DescriptionLink = _DescriptionLink;
    }

    public void set_IsUseful(int _IsUseful) {
        this._IsUseful = _IsUseful;
    }

    public void set_IsUseless(int _IsUseless) {
        this._IsUseless = _IsUseless;
    }
}
