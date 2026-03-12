package membership;

import java.time.LocalDate;

public class MemberRecord {

    private int memberId;
    private LocalDate joinDate;
    private int borrowedBookCount;

    public MemberRecord(int memberId) {
        this.memberId = memberId;
        this.joinDate = LocalDate.now();
    }
}
