// IMyAidlInterface.aidl
package com.mycompany.aidlTest;

import com.mycompany.aidlTest.Person;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addPerson(in Person person);

    List<Person> getPersonList();

    void stopServiceImmediately();
}
