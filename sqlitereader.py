# Kevin Chen
# This program checks for the presence of a sqlite3 file and
# prints a list of user names and a list of comments they have made.
# db.sqlite3 from my socialpy project is used as an example.

import os
import sqlite3
from sqlite3 import Error
import sys

# Check for the presence of database
def connection(db_file):
    if(os.path.exists(db_file)):
        try:
            conn = sqlite3.connect(db_file)
            return conn
        except Error as e:
            print e
            sys.exit(1)
    else:
        print db_file + " does not exist."
        sys.exit(1)

if __name__ == '__main__':
    # User types in path of the file or use the default
    if len(sys.argv) == 2:
        database = sys.argv[1]
    else:
        database = "./db.sqlite3"

    # Connect with the database
    conn = connection(database)
    cur = conn.cursor()

    try:
        # Read the auth_user table
        cur.execute("SELECT * FROM auth_user ORDER BY id")
        rows = cur.fetchall()

        print "Users:"
        # Users are in the fifth column of auth_user table
        for row in rows:
            print row[4]

    except Error as e:
        print e
        conn.close()
        sys.exit(1)

    # Create space between Users and Comments in the output
    print

    try:
        # Read the account_comment table
        cur.execute("SELECT * FROM account_comment ORDER BY created")
        rows = cur.fetchall()

        print "Comments:"
        # Comments are in the third colum of account_comment and
        # the author is the second column of the same table.
        for row in rows:
            print "\"" + row[2] + "\"" + " by: " + row[1]

    except Error as e:
        print e
        conn.close()
        sys.exit(1)

    conn.close()
