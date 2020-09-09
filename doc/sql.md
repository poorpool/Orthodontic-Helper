# 建表语句

```sql
create table Brace (
    id integer primary key autoincrement,
    name text,
    start_date text,
    ended integer,
    end_date text
)

create table BraceAndStop (
    id integer primary key autoincrement,
    brace_id integer,
    start_date text,
    end_date text,
    ended integer
)
```