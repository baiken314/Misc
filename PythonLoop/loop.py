list1 = [
    4,
    'string',
    7.8,
    [
        1,
        'sub1'
    ],
    'egg',
    [
        1,
        'sub1-1',
        [
            'sub2-2',
            2
        ],
        [
            'sub2-3',
            [
                'sub3',
                4,
                3,
                2,
                1,
                [
                    'syb4',
                    5,
                    [
                        'sub5'
                    ],
                    7,
                    8,
                    9
                ],
                'sub3'
            ],
            'sub2',
        ],
        'sub1'
    ],
    'end of list'
]

def print_all(p_list):
    for thing in p_list:
        if type(thing) is not list:
            print(thing)
        else:
            print_all(thing)

print_all(list1)
